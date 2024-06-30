import {Injectable} from '@angular/core';
import {MenuItem} from "primeng/api";

@Injectable({
  providedIn: 'root'
})
export class MenubarService {

  constructor() {
  }

  getMenubarItems(currentServerName: string):MenuItem[] {
    return [
      {
        label: 'Server',
        icon: 'pi pi-fw pi-server',
        items: [
          {
            label: 'Tree-Overview',
            icon: 'pi pi-fw pi-code'
          },
          {
            separator: true
          },
          {
            label: 'Switch server',
            icon: 'pi pi-fw pi-chevron-circle-right',
            items: [
              {
                label: 'Cloud Server',
                icon: 'pi pi-fw pi-server'
              },
              {
                label: 'Cloud XXL',
                icon: 'pi pi-fw pi-server'
              },

            ]
          }
        ]
      },
      {
        label: 'Database',
        icon: 'pi pi-fw pi-database',
        items: [
          {
            label: 'Edit',
            icon: 'pi pi-fw pi-pencil',
            items: [
              {
                label: 'Create',
                icon: 'pi pi-fw pi-plus'
              },
              {
                label: 'Delete',
                icon: 'pi pi-fw pi-trash'
              }
            ]
          }
        ]
      },
      {
        label: 'Table',
        icon: 'pi pi-fw pi-table',
        items: [
          {
            label: 'Edit',
            icon: 'pi pi-fw pi-pencil',
            items: [
              {
                label: 'Create',
                icon: 'pi pi-fw pi-plus'
              },
              {
                label: 'Delete',
                icon: 'pi pi-fw pi-trash'
              }
            ]
          }
        ]
      },
      {
        label:'SQL',
        icon: 'pi pi-fw pi-send'
      }
    ];
  }
}
